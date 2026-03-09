"use client";

import { useEffect, useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import { SeatGrid } from "@/src/components/SeatGrid";
import { api, SeatStatus } from "@/src/lib/api";

export default function SeatSelectionPage({
  params
}: {
  params: { eventId: string };
}) {
  const router = useRouter();
  const searchParams = useSearchParams();
  const eventId = Number(params.eventId);
  const venueId = Number(searchParams.get("venueId") ?? "1");
  const section = searchParams.get("section") ?? "VIP";

  const [seats, setSeats] = useState<Record<string, SeatStatus>>({});
  const [selected, setSelected] = useState<string[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    let cancelled = false;
    async function loadSeats() {
      setLoading(true);
      setError(null);
      try {
        const seatMap = await api.getSeatMap(eventId);
        if (!cancelled) {
          setSeats(seatMap as Record<string, SeatStatus>);
        }
      } catch (e) {
        if (!cancelled) {
          setError(
            e instanceof Error ? e.message : "Failed to load seats"
          );
        }
      } finally {
        if (!cancelled) setLoading(false);
      }
    }
    loadSeats();
    return () => {
      cancelled = true;
    };
  }, [eventId]);

  function toggleSeat(field: string) {
    setSelected((prev) =>
      prev.includes(field)
        ? prev.filter((f) => f !== field)
        : [...prev, field]
    );
  }

  async function handleContinue() {
    if (selected.length === 0) return;
    setLoading(true);
    setError(null);
    try {
      const booking = await api.bookSeats({
        venueId,
        section,
        seatFields: selected
      });
      router.push(
        `/checkout/${booking.id}?venueId=${venueId}&section=${section}&seats=${encodeURIComponent(
          selected.join(",")
        )}`
      );
    } catch (e) {
      setError(
        e instanceof Error ? e.message : "Failed to reserve seats"
      );
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="space-y-4">
      <h1 className="text-2xl font-semibold">
        Select seats – Event #{eventId}
      </h1>
      <p className="text-slate-300 text-sm">
        Venue {venueId}, Section {section}
      </p>
      {error && (
        <p className="text-sm text-rose-400 bg-rose-950/40 border border-rose-800 rounded px-3 py-2">
          {error}
        </p>
      )}
      {loading && <p className="text-sm text-slate-300">Loading…</p>}
      {!loading && (
        <>
          <SeatGrid
            seats={seats}
            selected={selected}
            onToggle={toggleSeat}
          />
          <button
            type="button"
            onClick={handleContinue}
            disabled={selected.length === 0 || loading}
            className="mt-4 px-4 py-2 rounded-md bg-sky-500 hover:bg-sky-400 disabled:opacity-60 text-slate-950 font-medium"
          >
            Continue to checkout
          </button>
        </>
      )}
    </div>
  );
}


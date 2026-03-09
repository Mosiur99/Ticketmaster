"use client";

import { useParams } from "next/navigation";
import { useState } from "react";
import { api } from "@/src/lib/api";

export default function VenueRedisPage() {
  const params = useParams<{ venueId: string }>();
  const venueId = Number(params.venueId);

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);

  async function handleInit() {
    setLoading(true);
    setMessage(null);
    setError(null);
    try {
      const res = await api.initSeatsInRedis(venueId);
      setMessage(res.message);
    } catch (e) {
      setError(
        e instanceof Error ? e.message : "Failed to initialize seats in Redis"
      );
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="space-y-4 max-w-lg">
      <h1 className="text-2xl font-semibold">
        Initialize Redis – Venue {venueId}
      </h1>
      <p className="text-slate-300 text-sm">
        This will load all seats for this venue into Redis and mark them as
        AVAILABLE.
      </p>
      {message && (
        <p className="text-sm text-emerald-400 bg-emerald-950/40 border border-emerald-800 rounded px-3 py-2">
          {message}
        </p>
      )}
      {error && (
        <p className="text-sm text-rose-400 bg-rose-950/40 border border-rose-800 rounded px-3 py-2">
          {error}
        </p>
      )}
      <button
        type="button"
        onClick={handleInit}
        disabled={loading}
        className="px-4 py-2 rounded-md bg-sky-500 hover:bg-sky-400 disabled:opacity-60 text-slate-950 font-medium text-sm"
      >
        {loading ? "Initializing..." : "Initialize seats in Redis"}
      </button>
    </div>
  );
}


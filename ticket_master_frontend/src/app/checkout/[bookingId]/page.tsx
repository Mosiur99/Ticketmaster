"use client";

import { useSearchParams, useRouter } from "next/navigation";
import { useState } from "react";
import { api } from "@/src/lib/api";

export default function CheckoutPage({
  params
}: {
  params: { bookingId: string };
}) {
  const bookingId = Number(params.bookingId);
  const searchParams = useSearchParams();
  const router = useRouter();

  const seats = (searchParams.get("seats") ?? "").split(",").filter(Boolean);
  const section = searchParams.get("section") ?? "VIP";
  const venueId = searchParams.get("venueId");

  const [amount, setAmount] = useState<number>(seats.length * 50);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  async function handlePay() {
    setLoading(true);
    setError(null);
    try {
      const booking = await api.payForBooking({
        bookingId,
        amount
      });
      router.push(`/confirmation/${booking.id}`);
    } catch (e) {
      setError(
        e instanceof Error ? e.message : "Payment failed, please try again"
      );
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="space-y-4 max-w-lg">
      <h1 className="text-2xl font-semibold">Checkout</h1>
      <div className="border border-slate-800 rounded-lg p-4 bg-slate-900/60 space-y-2">
        <div className="text-sm text-slate-300">
          <div>Booking ID: {bookingId}</div>
          {venueId && <div>Venue: {venueId}</div>}
          <div>Section: {section}</div>
          <div>Seats: {seats.join(", ") || "None selected"}</div>
        </div>
        <div className="mt-4 space-y-2">
          <label className="text-sm text-slate-200 flex flex-col gap-1">
            Amount
            <input
              type="number"
              value={amount}
              min={0}
              onChange={(e) => setAmount(Number(e.target.value))}
              className="px-3 py-2 rounded-md bg-slate-950 border border-slate-700 text-sm"
            />
          </label>
          {error && (
            <p className="text-sm text-rose-400 bg-rose-950/40 border border-rose-800 rounded px-3 py-2">
              {error}
            </p>
          )}
          <button
            type="button"
            onClick={handlePay}
            disabled={loading}
            className="w-full mt-2 px-4 py-2 rounded-md bg-emerald-500 hover:bg-emerald-400 disabled:opacity-60 text-slate-950 font-medium"
          >
            {loading ? "Processing..." : "Pay now"}
          </button>
        </div>
      </div>
    </div>
  );
}


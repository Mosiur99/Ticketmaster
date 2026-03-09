import Link from "next/link";

export default function ConfirmationPage({
  params
}: {
  params: { bookingId: string };
}) {
  const bookingId = Number(params.bookingId);

  return (
    <div className="space-y-4 max-w-lg">
      <h1 className="text-2xl font-semibold">Booking confirmed</h1>
      <p className="text-slate-300">
        Your booking has been confirmed. Your booking ID is{" "}
        <span className="font-mono font-semibold">{bookingId}</span>.
      </p>
      <Link
        href="/events"
        className="inline-flex px-4 py-2 rounded-md bg-sky-500 hover:bg-sky-400 text-slate-950 font-medium"
      >
        Back to events
      </Link>
    </div>
  );
}


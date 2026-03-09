import Link from "next/link";

export default function HomePage() {
  return (
    <section className="flex flex-col items-center justify-center gap-6 py-16">
      <h1 className="text-4xl font-semibold tracking-tight text-center">
        Book your next event
      </h1>
      <p className="text-slate-300 max-w-xl text-center">
        Browse events, pick your seats, and confirm your booking in a few
        simple steps.
      </p>
      <Link
        href="/events"
        className="px-6 py-2 rounded-md bg-sky-500 hover:bg-sky-400 text-slate-950 font-medium"
      >
        View events
      </Link>
    </section>
  );
}


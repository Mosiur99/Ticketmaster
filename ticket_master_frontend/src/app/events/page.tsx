import Link from "next/link";
import { api } from "@/src/lib/api";

export default async function EventsPage() {
  const data = await api.getEvents();

  return (
    <div className="space-y-4">
      <h1 className="text-2xl font-semibold">Events</h1>
      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
        {data.eventDTOS.map((event, index) => (
          <article
            key={index}
            className="border border-slate-800 rounded-lg p-4 flex flex-col justify-between bg-slate-900/60"
          >
            <div>
              <h2 className="font-medium text-lg">{event.name}</h2>
            </div>
            <div className="mt-4">
              <Link
                href={`/events/${index + 1}`}
                className="text-sm text-sky-400 hover:underline"
              >
                View seats
              </Link>
            </div>
          </article>
        ))}
      </div>
    </div>
  );
}


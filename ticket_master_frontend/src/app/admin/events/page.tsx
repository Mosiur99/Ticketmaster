import { api } from "@/src/lib/api";

export default async function AdminEventsPage() {
  const data = await api.getEvents();

  return (
    <div className="space-y-4">
      <h1 className="text-2xl font-semibold">Events</h1>
      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
        {data.eventDTOS.map((event, index) => (
          <article
            key={index}
            className="border border-slate-800 rounded-lg p-4 bg-slate-900/60"
          >
            <div className="font-medium text-lg">{event.name}</div>
            <div className="text-xs text-slate-400 mt-1">
              Event #{index + 1}
            </div>
          </article>
        ))}
      </div>
    </div>
  );
}


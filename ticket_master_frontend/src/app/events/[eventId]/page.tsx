import Link from "next/link";

interface EventDetailPageProps {
  params: { eventId: string };
  searchParams: { venueId?: string };
}

export default function EventDetailPage({
  params,
  searchParams
}: EventDetailPageProps) {
  const eventId = Number(params.eventId);
  const venueId = searchParams.venueId
    ? Number(searchParams.venueId)
    : 1;

  const sections = ["VIP", "PREMIUM", "REGULAR"];

  return (
    <div className="space-y-4">
      <h1 className="text-2xl font-semibold">Event #{eventId}</h1>
      <p className="text-slate-300">
        Choose a section to start selecting seats.
      </p>
      <div className="grid gap-3 md:grid-cols-3">
        {sections.map((section) => (
          <Link
            key={section}
            href={`/events/${eventId}/seats?venueId=${venueId}&section=${section}`}
            className="border border-slate-800 rounded-md p-4 hover:border-sky-500 transition-colors"
          >
            <div className="font-medium">{section}</div>
            <div className="text-xs text-slate-400 mt-1">
              Select seats in this section
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
}


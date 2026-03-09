import Link from "next/link";

export default function AdminPage() {
  return (
    <div className="space-y-4">
      <h1 className="text-2xl font-semibold">Admin</h1>
      <div className="grid gap-4 md:grid-cols-3">
        <Link
          href="/admin/venues"
          className="border border-slate-800 rounded-lg p-4 hover:border-sky-500 transition-colors"
        >
          <div className="font-medium">Venues</div>
          <div className="text-xs text-slate-400 mt-1">
            Initialize seat maps in Redis
          </div>
        </Link>
        <Link
          href="/admin/events"
          className="border border-slate-800 rounded-lg p-4 hover:border-sky-500 transition-colors"
        >
          <div className="font-medium">Events</div>
          <div className="text-xs text-slate-400 mt-1">
            Browse configured events
          </div>
        </Link>
      </div>
    </div>
  );
}


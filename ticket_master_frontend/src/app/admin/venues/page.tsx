import Link from "next/link";

export default function AdminVenuesPage() {
  // Backend currently has only venue create; listing can be added later.
  // For now, provide a link to a manual Redis init page where user can input venueId.
  return (
    <div className="space-y-4 max-w-lg">
      <h1 className="text-2xl font-semibold">Venues</h1>
      <p className="text-slate-300 text-sm">
        Initialize seat maps in Redis for a specific venue.
      </p>
      <Link
        href="/admin/venues/1/redis"
        className="inline-flex px-4 py-2 rounded-md bg-sky-500 hover:bg-sky-400 text-slate-950 font-medium text-sm"
      >
        Initialize Redis for venue 1
      </Link>
      <p className="text-xs text-slate-400">
        You can duplicate this page later to support a dynamic venue list once a
        venue listing API is available.
      </p>
    </div>
  );
}


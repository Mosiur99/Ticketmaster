import Link from "next/link";

export function Navbar() {
  return (
    <header className="border-b border-slate-800 bg-slate-900/70 backdrop-blur">
      <div className="container mx-auto px-4 py-3 flex items-center justify-between">
        <Link href="/" className="text-lg font-semibold tracking-tight">
          Ticket Master
        </Link>
        <nav className="flex gap-4 text-sm">
          <Link href="/events" className="hover:text-sky-300">
            Events
          </Link>
          <Link href="/admin" className="hover:text-sky-300">
            Admin
          </Link>
        </nav>
      </div>
    </header>
  );
}


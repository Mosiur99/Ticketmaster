import type { Metadata } from "next";
import "./globals.css";
import { ReactNode } from "react";
import { Navbar } from "@/src/components/Navbar";

export const metadata: Metadata = {
  title: "Ticket Master",
  description: "Simple ticket booking system"
};

export default function RootLayout({ children }: { children: ReactNode }) {
  return (
    <html lang="en">
      <body className="min-h-screen flex flex-col">
        <Navbar />
        <main className="flex-1 container mx-auto px-4 py-6">{children}</main>
      </body>
    </html>
  );
}


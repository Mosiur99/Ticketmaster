const baseUrl =
  process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8080";

async function fetchJson<T>(
  path: string,
  init?: RequestInit
): Promise<T> {
  const res = await fetch(`${baseUrl}${path}`, {
    ...init,
    headers: {
      "Content-Type": "application/json",
      ...(init?.headers || {})
    },
    cache: init?.cache ?? "no-store"
  });

  if (!res.ok) {
    const text = await res.text();
    throw new Error(text || `Request failed with ${res.status}`);
  }

  return res.json() as Promise<T>;
}

export const api = {
  getEvents: () =>
    fetchJson<EventSearchResponse>("/api/v1/event/search"),
  getEvent: (eventId: number) =>
    fetchJson<EventDTO>(`/api/v1/event/${eventId}`),
  getSeatMap: (eventId: number) =>
    fetchJson<Record<string, string>>(`/api/seats/${eventId}`),
  bookSeats: (body: SeatBookRequest) =>
    fetchJson<Booking>("/api/seats/book", {
      method: "POST",
      body: JSON.stringify(body)
    }),
  payForBooking: (body: PaymentRequest) =>
    fetchJson<Booking>("/api/v1/payment", {
      method: "POST",
      body: JSON.stringify(body)
    }),
  initSeatsInRedis: (venueId: number) =>
    fetchJson<ActionResponse>(
      `/api/v1/seat/set-in-redis?venueId=${venueId}`,
      { method: "POST" }
    )
};

export interface EventDTO {
  name: string;
}

export interface EventSearchResponse {
  eventDTOS: EventDTO[];
}

export type SeatStatus = "AVAILABLE" | "RESERVED" | "BOOKED";

export interface SeatBookRequest {
  venueId: number;
  section: string;
  seatFields: string[];
}

export interface Booking {
  id: number;
  bookingStatus: "PENDING" | "CONFIRMED" | "CANCELLED";
  totalPrice: number;
  createdAt: string;
}

export interface PaymentRequest {
  bookingId: number;
  amount: number;
}

export interface ActionResponse {
  result: boolean;
  message: string;
}


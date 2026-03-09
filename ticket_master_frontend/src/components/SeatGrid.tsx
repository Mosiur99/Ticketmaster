\"use client\";

import { SeatStatus } from \"@/src/lib/api\";

type SeatGridProps = {
  seats: Record<string, SeatStatus>;
  selected: string[];
  onToggle: (seatField: string) => void;
};

export function SeatGrid({ seats, selected, onToggle }: SeatGridProps) {
  const entries = Object.entries(seats);

  return (
    <div className=\"grid grid-cols-4 sm:grid-cols-6 md:grid-cols-8 gap-2\">
      {entries.map(([field, status]) => {
        const isSelected = selected.includes(field);
        const disabled = status === \"BOOKED\" || status === \"RESERVED\";

        let bg = \"bg-slate-800\";
        if (status === \"RESERVED\") bg = \"bg-amber-500/80\";
        if (status === \"BOOKED\") bg = \"bg-rose-500/80\";
        if (isSelected) bg = \"bg-sky-500\"; 

        return (
          <button
            key={field}
            type=\"button\"
            disabled={disabled}
            onClick={() => onToggle(field)}
            className={`${bg} text-xs sm:text-sm rounded px-2 py-1 text-slate-950 disabled:opacity-50 disabled:cursor-not-allowed`}
          >
            {field}
          </button>
        );
      })}
    </div>
  );
}


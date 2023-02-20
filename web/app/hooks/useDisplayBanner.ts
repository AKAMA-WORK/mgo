import { useMatches } from "@remix-run/react";


export function useDisplayBanner() {
  return useMatches().some((match) => {
    if (!match.handle) return false;

    let { handle, data } = match;

    // handle must be an object to continue
    if (typeof handle !== "object") return false;
    if (handle === null) return false;
    if (Array.isArray(handle)) return false;

    // get hydrate from handle (it may not exists)
    let displayBanner = handle.displayBanner as
      | undefined
      | boolean
      | ((data: unknown) => boolean);

    if (!displayBanner) return false;

    if (typeof displayBanner === "function") return displayBanner(data);
    return displayBanner;
  });
}
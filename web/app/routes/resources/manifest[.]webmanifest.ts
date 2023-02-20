import { json } from "@remix-run/node";
import type { LoaderFunction } from "@remix-run/node";
const icons= [
  {
    "src": "/icons/manifest-icon-192.maskable.png",
    "sizes": "192x192",
    "type": "image/png",
    "purpose": "any"
  },
  {
    "src": "/icons/manifest-icon-192.maskable.png",
    "sizes": "192x192",
    "type": "image/png",
    "purpose": "maskable"
  },
  {
    "src": "/icons/manifest-icon-512.maskable.png",
    "sizes": "512x512",
    "type": "image/png",
    "purpose": "any"
  },
  {
    "src": "/icons/manifest-icon-512.maskable.png",
    "sizes": "512x512",
    "type": "image/png",
    "purpose": "maskable"
  }
]

export let loader: LoaderFunction = () => {
  return json(
    {
      short_name: "M-GO",
      name: "M-GO",
      start_url: "/",
      display: "standalone",
      background_color: "#fff",
      theme_color: "#0D4D78",
      shortcuts: [
        {
          name: "Homepage",
          url: "/",
          icons
        },
      ],
      icons,
    },
    {
      headers: {
        "Cache-Control": "public, max-age=600",
        "Content-Type": "application/manifest+json",
      },
    },
  );
};

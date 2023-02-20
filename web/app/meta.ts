export type MetaFields =
  | "title"
  | "description"
  | "twitter:card"
  | "twitter:title"
  | "twitter:description"
  | "twitter:creator"
  | "twitter:image"
  | "og:title"
  | "og:description"
  | "og:image"
  | "og:url"
  | "og:type";

export const defaultTitle = "M-GO";

export const defaultDescription =
  "M-GO, Voyagez où vous voulez.";

export const defaultMeta: Partial<Record<MetaFields, string>> = {
  title: defaultTitle,
  description: defaultDescription,
  "og:title": defaultTitle,
  "og:description": defaultDescription,
  "og:type": "website",
 // "twitter:creator": "@cammchenry",
  "twitter:card": "summary",
  "twitter:title": defaultTitle,
  "twitter:description": defaultDescription,
};

type GenerateMeta = {
  title: string;
  description: string;
  image?: string;
  canonicalUrl?: string;
};

export const generateMeta = ({
  title,
  description,
  image,
  canonicalUrl,
}: GenerateMeta): Partial<Record<MetaFields, string>> => ({
  ...defaultMeta,
  title,
  description,
  "og:title": title,
  "og:type": "website",
  ...(canonicalUrl !== undefined && {
    "og:url": canonicalUrl,
  }),
  "og:description": description,
  "twitter:title": title,
  "twitter:description": description,
  ...(image !== undefined && {
    "og:image": image,
    "twitter:image": image,
    "twitter:card": "summary_large_image",
  }),
});

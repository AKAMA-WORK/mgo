export type Language = "fr" | "mg";

export function validateLanguage(language: any): language is Language {
  return language === "fr" || language === "mg";
}

export const supportedLanguages: Language[] = ['fr', 'mg'];
export const defaultLanguage: Language = 'fr'
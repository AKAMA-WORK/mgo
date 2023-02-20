import { Person } from "~/types/person/Person";

export const getUserFullName = (name: string, person?: Person) => {
    if (person?.firstName || person?.lastName) {
      if (person?.firstName && person?.lastName) {
        return `${person.firstName} ${person.lastName}`;
      }
  
      return person?.firstName || person?.lastName
    }
  
    return name;
  }
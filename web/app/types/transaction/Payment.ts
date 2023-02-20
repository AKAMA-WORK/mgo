import { PaymentMethod } from "./PaymentMethod";


export interface Payment {

  externalTransactionId?: string;


  amount: number;


  label: string;


  currency?: string; // Ar


  from?: string;


  to?: string;


  callbackUrl?: string;



  redirectUrl?: string;


  description?: string;

  method: PaymentMethod;
}

import { PaymentMethod } from "./PaymentMethod";
import { TransactionStatus } from "./TransactionStatus";

export interface Transaction {
  id: string;


  transactionId: string;


  externalTransactionId: string;


  amount: number;


  currency: string; // Ar


  from: string;

  to: string;


  callbackUrl: string;


  description: string;

  status: TransactionStatus;

  method: PaymentMethod;


  label: string;


  checkoutUrl: string;
}
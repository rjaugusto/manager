import { TransactionType } from "../transaction/TransactionType.enum";

export class BrazilianStock {
  id?: number;
  quantity: number;
  price: number;
  transactionType: TransactionType;
  transactionDate: Date;
  tax:number;
  ticker: string;

  constructor(
    quantity: number,
    price:number,
    transactionType: TransactionType,
    transactionDate: Date,
    tax: number,
    ticker: string,
    id?: number,){

		this.id = id;
    this.quantity = quantity;
    this.price =  price;
    this.transactionType = transactionType;
    this.transactionDate = transactionDate;
    this.tax = tax;
    this.ticker = ticker;
	}
}

import { AssetType } from "./AssetType.enum";
import { TransactionType } from "./TransactionType.enum";

export class Transaction {
  id: number;
  quantity: number;
  price: number;
  transactionType: TransactionType;
  assetType: AssetType;
  transactionDate: Date;

  constructor(id: number, quantity: number, price:number,transactionType: TransactionType,assetType: AssetType,transactionDate: Date){
		this.id = id;
    this.quantity = quantity;
    this.price =  price;
    this.transactionType = transactionType;
    this.assetType = assetType;
    this.transactionDate = transactionDate;
	}
}

import { MessageData } from "../MessageData";
import { SelectSeatsMessageDataSeat } from "./SelectSeatsMessageDataSeat";

export interface SelectSeatsMessageData extends Partial<MessageData>{
    seats: SelectSeatsMessageDataSeat[];
}
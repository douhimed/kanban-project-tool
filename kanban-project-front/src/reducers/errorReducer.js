import { GET_ERROS } from "../services/Types";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_ERROS:
      return action.payload;
    default:
      return state;
  }
}

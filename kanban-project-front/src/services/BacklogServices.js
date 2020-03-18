import axios from "axios";
import { GET_BACKLOG, GET_ERRORS } from "./Types";

const uri = "http://localhost:8080/api/backlog";

export const getBacklog = backlogId => async dispatch => {
  try {
    const res = await axios.get(`${uri}/${backlogId}`);
    dispatch({
      type: GET_BACKLOG,
      payload: res.data
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const addProjectTask = (backlogId, task, history) => async dispatch => {
  try {
    await axios.post(`${uri}/${backlogId}`, task);
    history.push(`/projects/${backlogId}`);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

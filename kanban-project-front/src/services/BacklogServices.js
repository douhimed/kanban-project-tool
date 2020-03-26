import axios from "axios";
import { GET_BACKLOG, GET_ERRORS, GET_TASK, DELETE_TASK } from "./Types";

const uri = "http://localhost:8081/api/backlog";

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

export const getTask = (
  backlogId,
  projectSequence,
  history
) => async dispatch => {
  try {
    const res = await axios.get(`${uri}/${backlogId}/${projectSequence}`);
    dispatch({
      type: GET_TASK,
      payload: res.data
    });
  } catch (error) {
    history.push("/projects");
  }
};

export const updateProjectTask = (
  backlogId,
  task,
  history
) => async dispatch => {
  try {
    await axios.put(`${uri}/${backlogId}/${task.projectSequence}`, task);
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

export const deleteTask = (backlogId, projectSequence) => async dispatch => {
  if (window.confirm("Are you sure you want delete this task ? ")) {
    await axios.delete(`${uri}/${backlogId}/${projectSequence}`);
    dispatch({
      type: DELETE_TASK,
      payload: projectSequence
    });
  }
};

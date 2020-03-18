import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./Types";

const uri = "http://localhost:8080/api/projects";

export const createProject = (project, history) => async dispatch => {
  try {
    await axios.post(uri, project);
    history.push("/projects");
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

export const getProjects = () => async dispatch => {
  const res = await axios.get(uri);
  dispatch({
    type: GET_PROJECTS,
    payload: res.data
  });
};

export const getProject = (identifier, history) => async dispatch => {
  try {
    const res = await axios.get(`${uri}/${identifier}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data
    });
  } catch (error) {
    history.push("/projects");
  }
};

export const updateProject = (project, history) => async dispatch => {
  try {
    await axios.put(uri, project);
    history.push("/projects");
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

export const deleteProject = identifier => async dispatch => {
  if (window.confirm("Are you sure you want delete this project ? ")) {
    await axios.delete(`${uri}/${identifier}`);
    dispatch({
      type: DELETE_PROJECT,
      payload: identifier
    });
  }
};

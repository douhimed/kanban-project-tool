import axios from "axios";
import { GET_ERRORS } from "./Types";

export const createProject = (project, history) => async dispatsh => {
  try {
    const res = await axios.post("http://localhost:8080/api/projects", project);
    history.push("/dashboard");
  } catch (error) {
    dispatsh({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

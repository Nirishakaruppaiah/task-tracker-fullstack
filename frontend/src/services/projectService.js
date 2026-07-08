import api from "./api";

export const getProjects = () => {
  return api.get("/projects");
};

export const createProject = (project) => {
  return api.post("/projects", project);
};

export const deleteProject = (id) => {
  return api.delete(`/projects/${id}`);
};
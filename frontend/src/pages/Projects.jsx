import { useEffect, useState } from "react";
import {
  getProjects,
  createProject,
  deleteProject,
} from "../services/projectService";

function Projects() {
  const [projects, setProjects] = useState([]);
  const [projectName, setProjectName] = useState("");

  const loadProjects = () => {
    getProjects()
      .then((response) => setProjects(response.data))
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    loadProjects();
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!projectName.trim()) return;

    createProject({ name: projectName })
      .then(() => {
        setProjectName("");
        loadProjects();
      })
      .catch((error) => console.error(error));
  };

  const handleDelete = (id) => {
    if (!window.confirm("Delete this project?")) return;

    deleteProject(id)
      .then(() => loadProjects())
      .catch((error) => console.error(error));
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Projects</h1>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Project Name"
          value={projectName}
          onChange={(e) => setProjectName(e.target.value)}
        />

        <button type="submit">Add Project</button>
      </form>

      <hr />

      {projects.length === 0 ? (
        <p>No Projects Found</p>
      ) : (
        <table border="1" cellPadding="10">
          <thead>
            <tr>
              <th>ID</th>
              <th>Project Name</th>
              <th>Tasks</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {projects.map((project) => (
              <tr key={project.id}>
                <td>{project.id}</td>
                <td>{project.name}</td>
                <td>{project.tasks?.length || 0}</td>
                <td>
                  <button onClick={() => handleDelete(project.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default Projects;
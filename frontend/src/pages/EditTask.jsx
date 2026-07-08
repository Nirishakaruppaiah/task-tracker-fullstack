import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

function EditTask() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [projects, setProjects] = useState([]);

  const [task, setTask] = useState({
    title: "",
    description: "",
    status: "TODO",
    priority: "HIGH",
    dueDate: "",
    projectId: "",
  });

  useEffect(() => {
    loadTask();
    loadProjects();
  }, []);

  const loadTask = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/tasks/${id}`);

      setTask({
        title: res.data.title,
        description: res.data.description,
        status: res.data.status,
        priority: res.data.priority,
        dueDate: res.data.dueDate,
        projectId: res.data.project.id,
      });
    } catch (err) {
      console.error(err);
    }
  };

  const loadProjects = async () => {
    try {
      const res = await axios.get("http://localhost:8080/projects");
      setProjects(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const handleChange = (e) => {
    setTask({
      ...task,
      [e.target.name]: e.target.value,
    });
  };

  const updateTask = async (e) => {
    e.preventDefault();

    try {
      await axios.put(`http://localhost:8080/tasks/${id}`, task);

      alert("Task Updated Successfully");

      navigate("/tasks");
    } catch (err) {
      console.error(err);
      alert("Update Failed");
    }
  };

  return (
    <div className="container">
      <h2>Edit Task</h2>

      <form onSubmit={updateTask}>

        <input
          type="text"
          name="title"
          placeholder="Title"
          value={task.title}
          onChange={handleChange}
          required
        />

        <br /><br />

        <textarea
          name="description"
          placeholder="Description"
          value={task.description}
          onChange={handleChange}
        />

        <br /><br />

        <select
          name="status"
          value={task.status}
          onChange={handleChange}
        >
          <option value="TODO">TODO</option>
          <option value="DOING">DOING</option>
          <option value="DONE">DONE</option>
        </select>

        <br /><br />

        <select
          name="priority"
          value={task.priority}
          onChange={handleChange}
        >
          <option value="HIGH">HIGH</option>
          <option value="MEDIUM">MEDIUM</option>
          <option value="LOW">LOW</option>
        </select>

        <br /><br />

        <input
          type="date"
          name="dueDate"
          value={task.dueDate}
          onChange={handleChange}
        />

        <br /><br />

        <select
          name="projectId"
          value={task.projectId}
          onChange={handleChange}
        >
          {projects.map((project) => (
            <option
              key={project.id}
              value={project.id}
            >
              {project.name}
            </option>
          ))}
        </select>

        <br /><br />

        <button type="submit">
          Update Task
        </button>

      </form>
    </div>
  );
}

export default EditTask;
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { createTask } from "../services/taskService";
import { getProjects } from "../services/projectService";

function AddTask() {
  const navigate = useNavigate();

  const [projects, setProjects] = useState([]);

  const [task, setTask] = useState({
    title: "",
    description: "",
    status: "TODO",
    priority: "HIGH",
    dueDate: "",
    projectId: ""
  });

  useEffect(() => {
    getProjects()
      .then((res) => {
        setProjects(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  const handleChange = (e) => {
    setTask({
      ...task,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    createTask(task)
      .then(() => {
        alert("Task Added Successfully");
        navigate("/tasks");
      })
      .catch((err) => {
        console.log(err);
        alert("Unable to add task");
      });
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Add Task</h2>

      <form onSubmit={handleSubmit}>

        <div>
          <label>Title</label><br />
          <input
            name="title"
            value={task.title}
            onChange={handleChange}
            required
          />
        </div>

        <br />

        <div>
          <label>Description</label><br />
          <textarea
            name="description"
            value={task.description}
            onChange={handleChange}
          />
        </div>

        <br />

        <div>
          <label>Status</label><br />
          <select
            name="status"
            value={task.status}
            onChange={handleChange}
          >
            <option value="TODO">TODO</option>
            <option value="DOING">DOING</option>
            <option value="DONE">DONE</option>
          </select>
        </div>

        <br />

        <div>
          <label>Priority</label><br />
          <select
            name="priority"
            value={task.priority}
            onChange={handleChange}
          >
            <option value="HIGH">HIGH</option>
            <option value="MEDIUM">MEDIUM</option>
            <option value="LOW">LOW</option>
          </select>
        </div>

        <br />

        <div>
          <label>Due Date</label><br />
          <input
            type="date"
            name="dueDate"
            value={task.dueDate}
            onChange={handleChange}
            required
          />
        </div>

        <br />

        <div>
          <label>Project</label><br />
          <select
            name="projectId"
            value={task.projectId}
            onChange={handleChange}
            required
          >
            <option value="">Select Project</option>

            {projects.map((p) => (
              <option key={p.id} value={p.id}>
                {p.name}
              </option>
            ))}

          </select>
        </div>

        <br />

        <button type="submit">
          Save Task
        </button>

      </form>
    </div>
  );
}

export default AddTask;
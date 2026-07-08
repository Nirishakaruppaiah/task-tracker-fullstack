import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getTasks, deleteTask } from "../services/taskService";

function Tasks() {
  const [tasks, setTasks] = useState([]);

  const loadTasks = () => {
    getTasks()
      .then((response) => {
        setTasks(response.data.content || response.data);
      })
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    loadTasks();
  }, []);

  const handleDelete = (id) => {
    if (!window.confirm("Are you sure you want to delete this task?")) {
      return;
    }

    deleteTask(id)
      .then(() => {
        alert("Task deleted successfully!");
        loadTasks();
      })
      .catch((error) => console.error(error));
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Tasks</h1>

      {tasks.length === 0 ? (
        <p>No Tasks Found</p>
      ) : (
        <table border="1" cellPadding="10">
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Status</th>
              <th>Priority</th>
              <th>Due Date</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {tasks.map((task) => (
              <tr key={task.id}>
                <td>{task.id}</td>
                <td>{task.title}</td>
                <td>{task.status}</td>
                <td>{task.priority}</td>
                <td>{task.dueDate}</td>

                <td>
  <Link to={`/edit-task/${task.id}`}>
    <button>Edit</button>
  </Link>

  {" "}

  <button onClick={() => handleDelete(task.id)}>
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

export default Tasks;
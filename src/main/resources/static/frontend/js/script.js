async function deleteTask() {
    const id= parseInt(document.getElementById("deletion-id").value)
    try {
        const response = await fetch(`http://localhost:8080/tasks/${id}`, {
            method: "DELETE"
        });

        if (response.status === 404) {
            alert("Task not found!");
            return;
        }

        if (!response.ok) {
            alert("Delete failed!");
            return;
        }

        alert("Task deleted!");

        showTasks()
    } catch (error) {
        console.error("Network error:", error);
        alert("Server unreachable!");
    }
}
async function editTask() {
    try {
        const id = parseInt(document.getElementById("edit-id").value);
        const statusValue = document.getElementById("edit-status").value;
        const priorityValue = document.getElementById("edit-priority").value;

        const task = {
            id: id,
            title: document.getElementById("edit-title").value,
            description: document.getElementById("edit-description").value,
            status: statusValue === "" ? null : statusValue,
            priority: priorityValue === "" ? null : priorityValue,
            dueDate: document.getElementById("edit-duedate").value
        };

        const response = await fetch(`http://localhost:8080/tasks/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        });

        if (response.status === 404) {
            alert("Task not found!");
            return;
        }

        if (!response.ok) {
            alert("Edit failed!");
            return;
        }

        alert("Task updated!");
        showTasks();

    } catch (error) {
        console.error(error);
        alert("Server error!");
    }
}

async function createTask() {
    try {
        const task = {
            id: parseInt(document.getElementById("creation-id").value),
            title: document.getElementById("creation-title").value,
            description: document.getElementById("creation-description").value,
            priority: document.getElementById("creation-priority").value,
            dueDate: document.getElementById("creation-duedate").value
        };

        const response = await fetch("http://localhost:8080/tasks", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        });

        const data = await response.text(); // או response.json()
        console.log(data);
        if(response.status===409){
            alert("ID already in use!");
            return
        }
        if (!response.ok) {
            alert("Create failed!");
            return;
        }
        alert("Task Created!");
        showTasks()
    } catch (error) {
        console.error("Error:", error);
    }

}


async function showTasks() {
    try {
        const response = await fetch('/tasks');
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const priority= document.getElementById("filter-priority").value
        const status= document.getElementById("filter-status").value
        const data = await response.json();
        const table= document.getElementById("tasks")
        table.innerHTML= "<tr>\n" +
            "            <th>Id</th>\n" +
            "            <th>Title</th>\n" +
            "            <th>Description</th>\n" +
            "            <th>Status</th>\n" +
            "            <th>Priority</th>\n" +
            "            <th>Due date</th>\n" +
            "        </tr>"
        data.forEach(task => {
            if(task.status==status || status==""){
                if(task.priority==priority || priority==""){
                    const tr= document.createElement("tr")
                    tr.innerHTML= "<td>"+task.id+"</td>"+"<td>"+task.title+"</td>"+"<td>"+task.description+"</td>"+"<td>"+task.status+"</td>"+"<td>"+task.priority+"</td>"+"<td>"+task.dueDate+"</td>";
                    table.append(tr);
                }
            }
        });
    } catch (error) {
        console.error('Fetch error:', error);
    }
}


window.addEventListener("DOMContentLoaded", () => {
    showTasks();
});
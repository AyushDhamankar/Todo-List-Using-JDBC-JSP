<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.company.data.UserStruct" %>
<%@ page import="com.company.data.TodoStruct" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
      <script src="https://cdn.tailwindcss.com"></script>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css">
      <style>
         .modal {
         display: none;
         position: fixed;
         top: 0;
         left: 0;
         width: 100%;
         height: 100%;
         background: rgba(0, 0, 0, 0.5);
         z-index: 1000;
         opacity: 0;
         transition: opacity 0.3s ease-in-out;
         }
         .modal.active {
         display: flex;
         opacity: 1;
         }
         .modal-content {
         background: #1f2937;
         padding: 1.5rem;
         border-radius: 1rem;
         width: 90%;
         max-width: 500px;
         margin: auto;
         transform: translateY(-50px);
         transition: transform 0.3s ease-in-out;
         }
         .modal.active .modal-content {
         transform: translateY(0);
         }
         /* Animation for task items */
         .task-item {
         transition: all 0.3s ease;
         }
         .task-item:hover {
         transform: translateX(5px);
         }
         /* Custom scrollbar */
         ::-webkit-scrollbar {
         width: 8px;
         }
         ::-webkit-scrollbar-track {
         background: ##252525;
         border-radius: 4px;
         }
         ::-webkit-scrollbar-thumb {
         background: #4b5563;
         border-radius: 4px;
         }
         ::-webkit-scrollbar-thumb:hover {
         background: #6b3880;
         }
      </style>
   </head>
   <body>
      <%
         HttpSession UserSession = request.getSession();
         if(UserSession != null && UserSession.getAttribute("user") != null) {
         %>
      <div class="bg-zinc-950 text-white min-h-screen">
         <div class="container mx-auto px-4 py-8 max-w-5xl">
            <div class="md:col-span-2">
               <div class="bg-[#111] rounded-xl p-4 lg:p-6 shadow-lg border border-purple-500/20">
                  <div class="flex justify-between items-center mb-6">
                     <h2 class="text-2xl font-bold">Active Tasks</h2>
                     <div style="display: flex; align-items: center;">
                     <button onclick="openModal('newTaskModal')" class="bg-gradient-to-r from-purple-500 to-pink-500 hover:from-purple-600 hover:to-pink-600 text-white px-4 py-2 rounded-lg flex items-center transition-all duration-300 transform hover:scale-105">
                     <i class="fas fa-plus mr-2"></i> New Task
                     </button>
                     <form action="GetTaskServlet" method="get">
                     <%
    				UserStruct user = (UserStruct) UserSession.getAttribute("user");
                    int userId = user.getUserId();
				  %>
               	 <input type="hidden" name="user_id" value="<%= user.getUserId() %>">
               	 <button style="padding-left: 15px;" type="submit">
               	 <i class="fa-solid fa-arrows-rotate"></i>
               	 </button>
               </form>
               </div>
                  </div>
                  <div id="taskList" class="space-y-4 h-[480px] max-h-full overflow-y-auto pr-2">
                  <%
                  	 TodoStruct[] todoArray = (TodoStruct[]) request.getAttribute("pendingtodoArray");
                     if (todoArray != null && todoArray.length > 0) {
                  %>
                <!-- <div id="taskList" class="space-y-4 h-[480px] max-h-full overflow-y-auto pr-2"> -->
                  <%
                     for (TodoStruct todo : todoArray) {
                     %>
                     <div class="task-item bg-zinc-900 hover:shadow-md hover:bg-zinc-800 rounded-lg p-4  transform transition-all duration-300">
                        <div class="flex flex-col lg:flex-row md:flex-row justify-between items-start">
                           <div>
                              <h3 class="font-bold "><%= todo.getTask() %></h3>
                              <p class="text-sm text-gray-400">Date of Creation: <%= todo.getCreatedDate() %></p>
                              <p class="text-sm text-gray-400">Due Date: <%= todo.getDueDate() %></p>
                           </div>
                           <div class="flex mt-1 lg:m-0 md:m-0 items-center space-x-2">
                              <form action="CompleteTaskServlet" method="post">
                              <input type="hidden" name="task_id" value="<%= todo.getTaskId() %>">
                              <button type="submit" class="px-2 py-1 bg-green-500/20 text-green-500 rounded-lg hover:bg-green-500/30 transition-colors duration-300">
                              <i class="fas fa-check"></i>
                              </button>
                              </form>
                              <button onclick="openModal1('newTaskModal1', '<%= todo.getTaskId() %>', '<%= todo.getTask() %>')" class="px-2 py-1 bg-blue-500/20 text-blue-500 rounded-lg hover:bg-blue-500/30 transition-colors duration-300">
                              <i class="fas fa-pen-to-square"></i>
                              </button>
                              <form action="DeleteTaskServlet" method="post">
                              <input type="hidden" name="task_id" value="<%= todo.getTaskId() %>">
                              <button type="submit" class="px-2 py-1 bg-red-500/20 text-red-500 rounded-lg hover:bg-red-500/30 transition-colors duration-300">
                              <i class="fas fa-trash"></i>
                              </button>
                              </form>
                           </div>
                        </div>
                     </div>
                     <%
                     }
                     %>
                     
                     <%
                     } else {
                     %>
                  <div class="flex justify-center mt-10">
                     <p class="text-xl text-gray-600">No pending todo available at the moment.</p>
                  </div>
                  <%
                     }
                     %>
                     
                     <div class="border border-purple-500/20"></div>
                     <h2 class="text-2xl font-bold">Completed Tasks</h2>
                     <div class="border border-purple-500/20"></div>
                     
                      <%
                  	 TodoStruct[] todoArray1 = (TodoStruct[]) request.getAttribute("completedtodoArray");
                     if (todoArray1 != null && todoArray1.length > 0) {
                  %>
                  <%
                     for (TodoStruct todo : todoArray1) {
                    	 System.out.println(todo.getTask());
                     %>
                     <div class="task-item bg-zinc-900 hover:shadow-md hover:bg-zinc-800 rounded-lg p-4  transform transition-all duration-300">
                        <div class="flex flex-col lg:flex-row md:flex-row justify-between items-start">
                           <div>
                              <h3 class="font-bold" style="text-decoration: line-through;"><%= todo.getTask() %></h3>
                              <p class="text-sm text-gray-400">Date of Creation: <%= todo.getCreatedDate() %></p>
                              <p class="text-sm text-gray-400">Due Date: <%= todo.getDueDate() %></p>
                              <p class="text-sm text-gray-400">Date of Completion: <%= todo.getCompletionDate() %></p>
                           </div>
                        </div>
                     </div>
                     <%
                     }
                     %>
                  <%
                     } else {
                     %>
                  <div class="flex justify-center mt-10">
                     <p class="text-xl text-gray-600">No completed todo available at the moment.</p>
                  </div>
                  <%
                     }
                     %>
                   
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- Custom Modal -->
      <div id="newTaskModal" class="modal">
         <div class="modal-content bg-zinc-800">
            <div class="flex justify-between items-center mb-4">
               <h3 class="text-xl text-white font-bold">New Task</h3>
               <button onclick="closeModal('newTaskModal')" class="text-gray-400 hover:text-white transition-colors">
               <i class="fas fa-times"></i>
               </button>
            </div>
            <form action="AddTaskServlet" method="post">
               <input type="hidden" name="user_id" value="<%= user.getUserId() %>">
               <div class="mb-4">
                  <label class="block text-sm text-white font-medium mb-2">Task</label>
                  <input type="text" id="taskTitle" name="task" class="w-full bg-zinc-700 rounded-lg p-2 text-white border border-gray-600 focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none transition-all duration-300">
               </div>
               <div class="mb-6">
                  <label class="block text-sm text-white font-medium mb-2">Due Date</label>
                  <input type="date" id="dueDate" name="due_date" class="w-full bg-zinc-700 rounded-lg p-2 text-white border border-gray-600 focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none transition-all duration-300">
               </div>
               <%
                  String status = (String) request.getAttribute("status");
                  
                  if (status == "success") {
                  %>
               <p style="color: green;">Task Added!!!</p>
               <%
                  } else if(status == "fail") {
                	  %>
                	  <p style="color: red;">Something went wrong!!!</p>
                	  <%
                  }
                  request.removeAttribute("status");
                  %>
               <div class="flex justify-end space-x-4">
                  <button type="button" onclick="closeModal('newTaskModal')" class="text-white px-4 py-2 bg-gray-700 rounded-lg hover:bg-gray-600 transition-colors duration-300">
                  Cancel
                  </button>
                  <button type="submit" class="text-white px-4 py-2 bg-gradient-to-r from-purple-500 to-pink-500 rounded-lg hover:from-purple-600 hover:to-pink-600 transition-all duration-300 transform hover:scale-105">
                  Create Quest
                  </button>
               </div>
            </form>
         </div>
      </div>
      
      <!-- Custom Update Modal -->
      <div id="newTaskModal1" class="modal">
         <div class="modal-content bg-zinc-800">
            <div class="flex justify-between items-center mb-4">
               <h3 class="text-xl text-white font-bold">New Task</h3>
               <button onclick="closeModal1('newTaskModal1')" class="text-gray-400 hover:text-white transition-colors">
               <i class="fas fa-times"></i>
               </button>
            </div>
            <form action="UpdateTaskServlet" method="post">
               <input type="hidden" name="task_id" id="taskId">
               <div class="mb-4">
                  <label class="block text-sm text-white font-medium mb-2">Old Task</label>
                  <input type="text" id="task" readonly class="w-full bg-zinc-700 rounded-lg p-2 text-white border border-gray-600 focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none transition-all duration-300">
               </div>
               <div class="mb-4">
                  <label class="block text-sm text-white font-medium mb-2">New Task</label>
                  <input type="text" id="taskTitle" name="new_task" required class="w-full bg-zinc-700 rounded-lg p-2 text-white border border-gray-600 focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none transition-all duration-300">
               </div>
               <div class="flex justify-end space-x-4">
                  <button type="button" onclick="closeModal1('newTaskModal1')" class="text-white px-4 py-2 bg-gray-700 rounded-lg hover:bg-gray-600 transition-colors duration-300">
                  Cancel
                  </button>
                  <button type="submit" class="text-white px-4 py-2 bg-gradient-to-r from-purple-500 to-pink-500 rounded-lg hover:from-purple-600 hover:to-pink-600 transition-all duration-300 transform hover:scale-105">
                  Create Quest
                  </button>
               </div>
            </form>
         </div>
      </div>
      <%
         } else {
         	response.sendRedirect("Login.jsp");
         }
         %>
      <script>
         //Modal functions
         function openModal(modalId) {
           const modal = document.getElementById(modalId);
           modal.classList.add("active");
           // Prevent body scrolling when modal is open
           document.body.style.overflow = "hidden";
         }
         
         function closeModal(modalId) {
           const modal = document.getElementById(modalId);
           modal.classList.remove("active");
           // Restore body scrolling
           document.body.style.overflow = "auto";
           // Reset form
           document.getElementById("newTaskForm").reset();
         }
         
       	 //Modal functions
         function openModal1(modalId, taskId, task) {
           const modal = document.getElementById(modalId);
           modal.classList.add("active");
           
           // Set the value of the hidden input field
           const taskid = document.getElementById("taskId");
           taskid.value = taskId;
           
           // Set the value of the hidden input field
           const taskInput = document.getElementById("task");
           taskInput.value = task;
           
           // Prevent body scrolling when modal is open
           document.body.style.overflow = "hidden";
         }
         
         function closeModal1(modalId) {
           const modal = document.getElementById(modalId);
           modal.classList.remove("active");
           // Restore body scrolling
           document.body.style.overflow = "auto";
           // Reset form
           document.getElementById("newTaskForm").reset();
         }
         
         // Close modal when clicking outside
         window.onclick = function (event) {
           if (event.target.classList.contains("modal")) {
             closeModal(event.target.id);
           }
         };
      </script>
   </body>
</html>
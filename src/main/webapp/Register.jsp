<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
      <script src="https://cdn.tailwindcss.com"></script>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css">
      <style>
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
      </style>
   </head>
   <body>
      <div class="min-h-screen flex items-center justify-center bg-[#09090b]">
         <div class="modal-content bg-zinc-800 border border-purple-500/20">
            <div class="flex justify-between items-center mb-4">
               <h3 class="text-xl text-white font-bold">Register User</h3>
            </div>
            <form action="UserRegisterServlet" method="post">
               <div class="mb-4">
                  <label class="block text-sm text-white font-medium mb-2">Username</label>
                  <input type="text" id="taskTitle" name="username" placeholder="Ayush" required class="w-full bg-zinc-700 rounded-lg p-2 text-white border border-gray-600 focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none transition-all duration-300"/>
               </div>
               <div class="mb-4">
                  <label class="block text-sm text-white font-medium mb-2">Password</label>
                  <input type="password" id="taskTitle" name="password" placeholder="1234" required class="w-full bg-zinc-700 rounded-lg p-2 text-white border border-gray-600 focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none transition-all duration-300"/>
               </div>
               <%
                  String status = (String) request.getAttribute("status");
                  
                  if (status != null) {
                  %>
               <p style="color: red;">Something went wrong!!!</p>
               <%
                  }
                  request.removeAttribute("status");
                  %>
               <div class="flex justify-center">
                  <button type="submit" class="text-white px-4 py-2 bg-gradient-to-r from-purple-500 to-pink-500 rounded-lg hover:from-purple-600 hover:to-pink-600 transition-all duration-300 transform hover:scale-105">
                  Sign Up
                  </button>
               </div>
            </form>
         </div>
      </div>
   </body>
</html>
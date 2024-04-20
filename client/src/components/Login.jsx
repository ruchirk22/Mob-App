import React from 'react';
import { useState } from 'react';
import { Link } from "react-router-dom";

const Login = () => {
  
  return (
    <div className="flex justify-center items-center h-screen">
      <form className="bg-white shadow-md rounded-lg px-8 py-10 sm:w-96"
      style={{
        boxShadow: '0 0 10px 5px rgba(0, 0, 255, 0.1)', // Adjust the values and color as needed
      }}
      >
      <h2 className="text-3xl font-bold text-center text-gray-800 mb-8">Welcome Back!</h2>
        <div className="mb-6">
          <label className="block text-gray-700 text-sm font-semibold mb-2" htmlFor="username">
            Username
          </label>
          <input className="shadow-md border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text" placeholder="Username" />
        </div>
        <div className="mb-6">
          <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">
            Password
          </label>
          <input className="shadow-md border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="password" type="password" placeholder="Password" />
        </div>
        <div className="flex items-center justify-between">
        <Link to="/dashboard" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button">Sign In</Link>
        </div>
      </form>
    </div>
  )
}

export default Login

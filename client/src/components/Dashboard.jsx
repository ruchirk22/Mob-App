import React from 'react';
import { Link } from "react-router-dom";

const Dashboard = () => {
  return (
    <div className="flex">
        <div className="w-1/4 bg-gray-200">
          <h2 className="text-lg font-bold p-4">User Dashboard</h2>
            <ul>
              <li className="mb-2">
                <Link to="/plans" className="text-blue-500 hover:text-blue-700">All Plans</Link>
              </li>
              <li className="mb-2">
                <Link to="/plans-category" className="text-blue-500 hover:text-blue-700">Plans by Category</Link>
              </li>
            </ul>
        </div>
        </div>
  )
}

export default Dashboard

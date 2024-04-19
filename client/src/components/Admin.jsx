import React from 'react';
import { Link } from "react-router-dom";

const Admin = () => {
  return (
    <div className="flex">
        <div className="w-1/4 bg-gray-200">
          <h2 className="text-lg font-bold p-4">Admin Dashboard</h2>
            <ul>
              <li className="mb-2">
                <Link to="/users" className="text-blue-500 hover:text-blue-700">All Users</Link>
              </li>
              <li className="mb-2">
                <Link to="/expiring-plans" className="text-blue-500 hover:text-blue-700">Expiring Plans</Link>
              </li>
            </ul>
        </div>
        </div>
  )
}

export default Admin;

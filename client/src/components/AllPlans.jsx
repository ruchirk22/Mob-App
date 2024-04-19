import React from 'react';
import { Link } from "react-router-dom";

const AllPlans = () => {
    const planList = [
        { id: 1, name: 'Basic Plan', category: 'Basic' },
        { id: 2, name: 'Premium Plan', category: 'Premium' },
      ];
  return (
    <div>
      <h2 className="text-lg font-bold mb-4">All Plans</h2>
      <ul>
        {planList.map(plan => (
          <li key={plan.id} className="mb-2">
            <Link to={`/plans/${plan.id}`} className="text-blue-500 hover:text-blue-700">{plan.name}</Link>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default AllPlans

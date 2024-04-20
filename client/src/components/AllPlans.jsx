import React from 'react';
import { Link } from "react-router-dom";

const AllPlans = () => {
    const planList = [
        { id: 1, name: 'Basic Plan', category: 'Basic' },
        { id: 2, name: 'Premium Plan', category: 'Premium' },
      ];
      return (
        <div className="mt-12 p-12 bg-white rounded-lg shadow-xl">
        <h2 className="text-3xl font-bold mb-6 text-gray-800">All Plans</h2>
        <ul>
            {planList.map(plan => (
                <li key={plan.id} className="mb-4">
                    <Link
                        to={`/plans/${plan.id}`}
                        className="block px-4 py-12 rounded-lg bg-blue-100 hover:bg-blue-200 text-blue-800 font-semibold transition duration-300 ease-in-out transform hover:scale-105 hover:shadow-lg"
                    >
                        {plan.name}
                    </Link>
                </li>
            ))}
        </ul>
    </div>
    );
}

export default AllPlans

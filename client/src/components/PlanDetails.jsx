import React from 'react';
import { useParams } from 'react-router-dom';
import { Link } from "react-router-dom";

const PlanDetails = () => {
    const { planId } = useParams();
  return (
    <div className="p-6 bg-gray-100 rounded-lg shadow-md animate-fadeIn">
            <h2 className="text-3xl font-bold mb-6 text-gray-800">Plan Details</h2>
            <p className="text-lg text-gray-600 mb-8">Plan ID: {planId}</p>
            <Link
                to="/payment"
                className="inline-block bg-blue-300 hover:bg-blue-400 text-white font-semibold py-3 px-6 rounded-md transition duration-300 ease-in-out shadow-md hover:shadow-lg"
            >
                Buy Plan
            </Link>
     </div>
  )
}

export default PlanDetails

import React from 'react';
import { useParams } from 'react-router-dom';
import { Link } from "react-router-dom";

const PlanDetails = () => {
    const { planId } = useParams();
  return (
    <div>
      <h2 className="text-lg font-bold mb-4">Plan Details</h2>
      <p className='p-10'>Plan ID: {planId}</p>
      <Link to="/payment" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Buy Plan</Link>
    </div>
  )
}

export default PlanDetails

import React from 'react';
import { Link } from "react-router-dom";

const Payment = () => {
  const handleSubmit = (e) => {
    e.preventDefault();
    // Add logic to handle payment submission
  };
  return (
    <div class="flex items-center justify-center h-screen">
    <div class="max-w-md mx-auto">
        <form class="bg-white shadow-md rounded-lg px-8 pt-6 pb-8 mb-4 transition duration-300 hover:shadow-xl">
            <h2 class="text-2xl font-bold mb-4 text-gray-800">Payment Details</h2>
            <div class="mb-4">
                <label for="cardNumber" class="block text-sm font-semibold text-gray-700 mb-2">Card Number</label>
                <input
                    id="cardNumber"
                    type="text"
                    placeholder="Card Number"
                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                />
            </div>
            <div class="mb-4 grid grid-cols-2 gap-4">
                <div>
                    <label for="expiryDate" class="block text-sm font-semibold text-gray-700 mb-2">Expiry Date</label>
                    <input
                        id="expiryDate"
                        type="text"
                        placeholder="MM/YY"
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                </div>
                <div>
                    <label for="cvv" class="block text-sm font-semibold text-gray-700 mb-2">CVV</label>
                    <input
                        id="cvv"
                        type="text"
                        placeholder="CVV"
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                </div>
            </div>
            <div class="flex items-center justify-between mt-6">
		<Link to="/payment-summary" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">Pay Now</Link>
            </div>
        </form>
    </div>
</div>
  )
}

export default Payment

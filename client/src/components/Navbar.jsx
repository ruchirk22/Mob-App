import React from 'react';
import { Link } from "react-router-dom";

const Navbar = () => {
    return (
        <nav className="bg-gradient-to-r from-blue-500 to-purple-500 shadow-lg py-4">
            <div className="container mx-auto flex items-center justify-between">
                <div className="flex items-center">
                    <span className="text-white font-bold text-xl mr-4 font-serif">Mobile Recharge App</span>
                </div>
                <div className="hidden md:block">
                    <ul className="flex space-x-4">
                        <li><Link to="/" className="text-white hover:text-gray-200 transition duration-300 ease-in-out py-2 px-4 rounded-lg font-medium">Home</Link></li>
                        <li><Link to="/login" className="text-white hover:text-gray-200 transition duration-300 ease-in-out py-2 px-4 rounded-lg font-medium">Login</Link></li>
                        <li><Link to="/register" className="text-white hover:text-gray-200 transition duration-300 ease-in-out py-2 px-4 rounded-lg font-medium">Register</Link></li>
                        <li><Link to="/payment" className="text-white hover:text-gray-200 transition duration-300 ease-in-out py-2 px-4 rounded-lg font-medium">Payment</Link></li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
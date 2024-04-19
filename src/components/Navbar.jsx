import React from 'react';
import { Link } from "react-router-dom";

const Navbar = () => {
    return (
        <nav className="bg-gray-800 p-4">
            <div className="max-w-7xl mx-auto">
                <div className="flex items-center justify-between">
                    <div className="flex items-center">
                        <span className="text-white font-bold text-lg">Mobile Recharge App</span>
                    </div>
                    <div className="hidden md:block">
                        <ul className="flex space-x-4">
                            <li><Link to="/" className="text-gray-300 hover:text-white">Home</Link></li>
                            <li><Link to="/login" className="text-gray-300 hover:text-white">Login</Link></li>
                            <li><Link to="/register" className="text-gray-300 hover:text-white">Register</Link></li>
                            <li><Link to="/payment" className="text-gray-300 hover:text-white">Payment</Link></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
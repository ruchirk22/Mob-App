import React from 'react'
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';

const Home = () => {
  return (
    
    <div className="container mx-auto text-center py-12">
      <Carousel autoPlay infiniteLoop showThumbs={false} showStatus={false} interval={5000} className="mb-8" showArrows={false}>
        <div>
          <img src="https://assets.airtel.in/static-assets/home-recast/images/hero-banner-2.png" alt="Slide 1" style={{ width: "1000px", height: "500px" }} className="w-full h-auto rounded-lg"/>
        </div>
        <div>
          <img src="https://c.ndtvimg.com/2023-04/la72jor_airtel-xstream_625x300_28_April_23.jpg" alt="Slide 2" style={{ width: "1000px", height: "500px" }} className="w-full h-auto rounded-lg"/>
        </div>
        <div>
          <img src="https://biznext.in/blog/wp-content/uploads/2023/04/The-Future-of-Mobile-Recharge-Businesses-in-India-1.webp" alt="Slide 3" style={{ width: "1000px", height: "500px" }} className="w-full h-auto rounded-lg"/>
        </div>
      </Carousel>
      <h1 className="text-4xl font-bold mb-8 text-gray-800 uppercase font-serif bg-gradient-to-r from-blue-900 via-blue-500 to-blue-200 
  text-transparent bg-clip-text">Welcome to Mobile Prepaid Recharge</h1>
      <p className="text-lg text-gray-600 mb-12 font-mono">Recharge your prepaid mobile instantly!</p>
      <form className="max-w-sm mx-auto">
        <div className="mb-6">
          <label htmlFor="phoneNumber" className="block text-gray-700 text-sm font-bold mb-2">Phone Number:</label>
          <input type="text" id="phoneNumber" placeholder="Enter your phone number" className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
        </div>
        <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline transition duration-300 w-full">Recharge Now</button>
      </form>
      <footer className="bg-gray-500 bg-opacity-40 text-white py-6 mt-12 rounded-t-lg shadow-lg">
        <div className="container mx-auto">
          <p className="text-center">&copy; {new Date().getFullYear()} Mobile Prepaid. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
};


const containerStyle = {
  textAlign: "center",
  padding: "20px",
};

const headingStyle = {
  fontSize: "3rem",
  marginBottom: "20px",
  color: "#333",
  textTransform: "uppercase", // Transform text to uppercase
};

const paragraphStyle = {
  fontSize: "1.5rem",
  marginBottom: "30px",
  color: "#666",
};

const formStyle = {
  maxWidth: "400px",
  margin: "0 auto",
};

const labelStyle = {
  fontSize: "1.2rem",
  marginBottom: "10px",
  color: "#333",
  display: "block", // Make labels block-level elements for better spacing
};

const inputStyle = {
  marginBottom: "20px",
  padding: "10px",
  borderRadius: "5px",
  border: "1px solid #ccc",
  fontSize: "1rem",
  width: "100%",
  boxSizing: "border-box", // Include padding and border in input width calculation
};

const buttonStyle = {
  padding: "12px 24px",
  borderRadius: "5px",
  backgroundColor: "#007bff",
  color: "#fff",
  fontSize: "1.2rem",
  cursor: "pointer",
  border: "none",
  transition: "background-color 0.3s, color 0.3s",
  width: "100%",
  boxShadow: "0px 4px 8px rgba(0, 0, 0, 0.1)", // Add box shadow for depth
  outline: "none", // Remove default button outline
};



export default Home

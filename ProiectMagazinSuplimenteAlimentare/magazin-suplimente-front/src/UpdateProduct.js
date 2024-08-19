import React from "react";
import axiosInstance from "./axios";
import { useParams, useNavigate } from 'react-router-dom';

class UpdateProduct extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            product: {
                id: 0,
                name: "",
                price: 0,
                description: "",
                image: "",
                quantity: 0
            },
            isLoaded: false
        };
    }

    componentDidMount() {
        const { id } = this.props.match.params; // If using React Router v6, use `useParams()` hook outside of class component
        axiosInstance.get(`/product/${id}`)
            .then(res => {
                this.setState({ product: res.data, isLoaded: true });
            })
            .catch(error => {
                console.error("Error fetching product", error);
            });
    }

    handleChange = (event) => {
        const { name, value } = event.target;
        this.setState(prevState => ({
            product: {
                ...prevState.product,
                [name]: value
            }
        }));
    };

    handleSubmit = (event) => {
        event.preventDefault();
        const { id } = this.state.product;
        axiosInstance.put(`/product/${id}`, this.state.product)
            .then(res => {
                console.log("Product updated successfully", res.data);
                // Optionally navigate back to product list or details page
            })
            .catch(error => {
                console.error("Failed to update product", error);
            });
    };

    render() {
        const { name, price, description, image, quantity } = this.state.product;
        if (!this.state.isLoaded) return <div>Loading...</div>;

        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Name:
                    <input type="text" name="name" value={name} onChange={this.handleChange} />
                </label>
                <label>
                    Price:
                    <input type="number" name="price" value={price} onChange={this.handleChange} />
                </label>
                <label>
                    Description:
                    <textarea name="description" value={description} onChange={this.handleChange} />
                </label>
                <label>
                    Image URL:
                    <input type="text" name="image" value={image} onChange={this.handleChange} />
                </label>
                <label>
                    Quantity:
                    <input type="number" name="quantity" value={quantity} onChange={this.handleChange} />
                </label>
                <button type="submit">Update Product</button>
            </form>
        );
    }
}

export default UpdateProduct;

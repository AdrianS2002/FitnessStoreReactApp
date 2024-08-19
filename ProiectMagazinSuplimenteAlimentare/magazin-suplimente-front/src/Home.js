import React from "react";
import axiosInstance from "./axios";
import { withTranslation } from 'react-i18next';
import './fundal.css'; 
class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
            product: {
                id: 0,
                name: "",
                price: 0,
                description: "",
                category: "",
                image: "",
                quantity: 0
            }
        };
    }

    componentDidMount() {
        axiosInstance.get("/product")
            .then(res => {
                this.setState({ products: res.data });
                console.log("Success", this.state.products);
            })
            .catch(error => {
                console.log("Error", error);
                this.setState({ products: [] });
            });
    }

    render() {
        const { t } = this.props; // Extracting the translation function
        const headingStyle = {
            background: 'linear-gradient(to right, #fc1806,#effd1d ,#e61dfd)',
            WebkitBackgroundClip: 'text',
            WebkitTextFillColor: 'transparent',
            textAlign: 'center',
            fontSize: '2.5em',
            textShadow: '2px 2px 8px rgba(194, 164, 16,0.3)'
        };
        return (
            <div className="backgroundImage">
                <h1 style={headingStyle}>{t('WELCOME TO OUR STORE!!!')}</h1>
            </div>
        );
    }
}

export default withTranslation()(Home);

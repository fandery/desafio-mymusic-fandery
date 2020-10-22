import styled from 'styled-components'

export const Section = styled.div`    
    display: flex;
    flex-direction: column;
    height: 300px;
`
export const PanelSearch = styled.div`    
    display: flex;
    flex-direction: column;
`
export const Header = styled.div`    
    font-family: Montserrat, sans-serif;
    font-size: 30px;
    line-height: 30px;
    font-weight: 700;    
    text-align: center;  
    margin-top: 30px;  
`

export const SearchUser = styled.div`    
    background-color: red;
    display: flex;            
`
export const Container = styled.div`    
    position: static;
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	overflow: hidden;
	width: 80%;
	height: 85%;
	margin-right: auto;
	margin-left: auto;
	-webkit-box-align: stretch;
	-webkit-align-items: stretch;
	-ms-flex-align: stretch;
	align-items: stretch;
	border-radius: 3px;
	background-color: #f8f8f8;
    box-shadow: 12px 12px 20px -18px #000;
    margin-top: 50px;
    flex-direction: column;
`
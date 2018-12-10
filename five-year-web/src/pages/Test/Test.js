import React,{Component} from 'react';
import {Card} from 'antd';

export default class TestFrom extends Component{
  constructor(props) {
    super(props);
    this.state = {data:[]};
  }

  componentDidMount(){
    fetch('./mock/order.json').then(res=>{
      if(res.ok){
        res.json().then(data=>{
          this.setState({
            data
          })
        })
      }
    })
  }

  render() {
    return (
      <div>
        {
          this.state.data.map(card => {
            return (
              <Card key={card.id}>
                <div>Q: {card.setup}</div>
                <div>
                  <strong>A: {card.punchline}</strong>
                </div>
              </Card>
            );
          })
        }
      </div>
    );
  }
}

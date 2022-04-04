const { ApolloServer } = require('apollo-server');
const { ApolloGateway } = require('@apollo/gateway')

const gateway = new ApolloGateway({
  serviceList: [
    { name: 'products', url: 'http://localhost:8081/products/graphql' },
    { name: 'catalogs', url: 'http://localhost:8082/catalogs/graphql' },
  ]
});

const server = new ApolloServer({
  gateway,
});


server.listen();

console.log(`🚀 Apollo Gateway now ready at http://localhost:4000/graphql`);

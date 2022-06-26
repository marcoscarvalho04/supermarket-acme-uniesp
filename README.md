# supermarket-acme-uniesp
Repositório para guardar os códigos do projeto do supermarket acme feito na pós do uniesp.

# Estrutura
### api-gateway e service-registry

#### api-gateway

serve para fazer o roteamento dos serviços para fora, fazendo com que o cliente precise ter apenas um único ponto de acesso aos endpoints criados. Deve ser levantado 
primeiro em relação aos outros projetos. 

### service-registry 
Registra no api-gateway todos os serviços que estão disponíveis no projeto. Cada projeto vai inevitavelmente se registrar através dele para ser enxergado pelo api-gateway

# Fraud, notification e clients 

São os serviços que podem ser utilizados. O enfoque aqui não foi fazer uma regra de negócios muito robusta, mas sim mostrar como podemos, através da stack spring
fazer com que esses serviços subam usando um api-gateway, o zipkin (para logar e verificar os traces das chamadas aos microserviços) e o redirecionamento, como foi
explicado na seção anterior. Nesse caso, o sistema consiste em um cadastro de clientes, de notificações para os clientes e um banco de fraude. Para cada cadastro de 
cliente, o sistema verifica se ele é uma fraude mesmo e, caso não seja, ele é cadatrado normalmente. Como disse, o enfoque não é na regra de negócio, pois muito
se pode melhorar nisso, mas no fato de que usamos a stack spring para colocar um sistema robusto de gerenciamento das chamadas das apis através dessa tecnolgia, tornando-o um pouco mais profissional. 

# clients 
É utilizado para fazer as chamadas internas, encapsulando através do feng client as chamadas que as API's farão internamente para consumir recursos que precisam 
de outro endpoint. Provê esse tipo de serviço como uma camada transparente para os endpoints. 

## é necessário utilizar o docker para subir os serviços necessários para a utilização dos microserviços. É possível usar o docker-compose para isso. 



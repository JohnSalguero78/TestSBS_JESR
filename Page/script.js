document.addEventListener('DOMContentLoaded', function () {
  const testimonialContent = document.getElementById('testimonial-content');

   axios.get('https://jsonplaceholder.typicode.com/posts')
    .then(postsResponse => {
      const posts = postsResponse.data.slice(0, 4); 
      return axios.get('https://jsonplaceholder.typicode.com/users')
        .then(usersResponse => {
          const users = usersResponse.data;
          const testimonials = posts.map((post, index) => {
            const user = users.find(user => user.id === post.userId);
            return {
              title: post.title,
              body: post.body,
              name: user.name,
              company: user.company.name,
              image: `assets/images/person_${index + 1}.jpg`
            };
          });

          // Inject testimonials into the carousel
          testimonials.forEach((testimonial, index) => {
            const carouselItem = document.createElement('div');
            carouselItem.classList.add('carousel-item');
            if (index === 0) {
              carouselItem.classList.add('active');
            }
            carouselItem.innerHTML = `
              <img src="${testimonial.image}" alt="Testimonial Image" class="d-block w-100">
              <h5>${testimonial.title}</h5>
              <p>"${testimonial.body}"</p>
              <p><strong>${testimonial.name}</strong>, ${testimonial.company}</p>
            `;
            testimonialContent.appendChild(carouselItem);
          });
        });
    })
    .catch(error => {
      console.error('Error fetching testimonials:', error);
    });
});

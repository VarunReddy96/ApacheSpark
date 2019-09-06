<h2>Largest Triangle</h2>
<p><a name="system"></a></p>
<p>Given a group of two-dimensional points, we want to find the&nbsp;<strong>largest triangle,</strong>&nbsp;namely three distinct points that are the vertices of a triangle with the largest area. The area of a triangle is (<em>s</em>(<em>s</em>&nbsp;&minus;&nbsp;<em>a</em>)(<em>s</em>&nbsp;&minus;&nbsp;<em>b</em>)(<em>s</em>&nbsp;&minus;&nbsp;<em>c</em>))<sup>1/2</sup>, where&nbsp;<em>a</em>,&nbsp;<em>b</em>, and&nbsp;<em>c</em>&nbsp;are the lengths of the triangle's sides and&nbsp;<em>s</em>&nbsp;=&nbsp;(<em>a</em>&nbsp;+&nbsp;<em>b</em>&nbsp;+&nbsp;<em>c</em>)/2. The length of a triangle's side is the Euclidean distance between the side's two endpoints.</p>
<p>You will write sequential and cluster parallel versions of a program that finds the largest triangle in a group of points. The program's command line argument is a&nbsp;<em>constructor expression</em>&nbsp;for a PointSpec object from which the program obtains the points' (<em>x,y</em>) coordinates. </p>
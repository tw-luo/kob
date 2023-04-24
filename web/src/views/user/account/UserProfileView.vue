<template>
  <div class="container">
    <div class="row">
      <div class="col-3">
        <div class="card" style="margin-top: 20px">
          <div class="card-body">
            <img :src="$store.state.user.photo" alt="" style="width: 100%" />
          </div>
        </div>
      </div>

      <div class="col-9">
        <div class="card" style="margin-top: 20px">
          <div class="card-body">
            
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex';

export default{
    name: 'UserProfileView',
    setup() {
        const store = useStore();
        return { store };
    },
}

</script>
